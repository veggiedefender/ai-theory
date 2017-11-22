"""
Use naive bayes to calculate the probabilities of inputted numbers
based on a training data set provided in the file data.txt
"""
import math
import statistics

def read_data(filename):
    """
    Read data from the file into a dictionary of lists
    """
    # using dict instead of list because dict doesn't care about out
    # of order inputs, gaps in class names, or strings for class names
    categories = {}
    with open(filename) as file:
        lines = file.readlines()
    for line in lines:
        line = line.split()
        category = line[0]
        point = list(map(float, line[1:]))
        try:
            categories[category].append(point)
        except KeyError:
            categories[category] = [point]
    return categories

def gaussian_nb_total(points, num_points, inputs):
    """
    Find the probability the given input being part of a class
    using gaussian naive bayes
    """
    probability = len(points) / num_points
    for dim in [0, 1]:
        points_in_dim = [point[dim] for point in points]
        probability *= gaussian_nb_dim(points_in_dim, inputs[dim])
    return probability

def gaussian_nb_dim(points, value):
    """
    Find the probability of one dimension of the given input
    being part of one dimension of the class's points
    """
    mean = statistics.mean(points)
    variance = statistics.variance(points)
    return (
        math.exp(-(value - mean)**2 / (2 * variance)) /
        math.sqrt(2 * math.pi * variance)
    )

inputs = [float(input("Enter X:\n")), float(input("Enter Y:\n"))]
categories = read_data("data.txt").items()

num_points = 0
for _, points in categories:
    num_points += len(points)

for category, points in categories:
    probability = gaussian_nb_total(points, num_points, inputs)
    print(f"Class {category} probability: {probability}")
