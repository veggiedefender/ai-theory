"""
Implements XOR using a feedforward neural network
"""
import numpy as np

class Neuron:
    """
    Neuron class that takes a layer of inputs, weights, and a bias
    """
    def __init__(self, inputs, weights, bias):
        self.inputs = inputs
        self.weights = weights
        self.bias = bias

    @staticmethod
    def activation(x):
        """
        Calculates value of sigmoid function applied to a number
        """
        return 1 / (1 + np.exp(-x))

    def resolve(self):
        """
        Resolve the value of a neuron
        """
        inputs = [ neuron.resolve() for neuron in self.inputs ]
        return Neuron.activation(np.dot(inputs, self.weights) + self.bias)


class InputNeuron(Neuron):
    """
    Special type of neuron that holds an input value and has no input layer
    """
    def __init__(self, value):
        self.value = value

    def resolve(self):
        return self.value


input_layer = [
    InputNeuron(0),
    InputNeuron(1)
]
hidden_layer = [
    Neuron(input_layer, [5, -6], -3),
    Neuron(input_layer, [-6, 6], -3)
]
output_layer = Neuron(hidden_layer, [10, 10], -5)

print(output_layer.resolve())
