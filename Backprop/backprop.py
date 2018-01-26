"""Feedforward neural network that trains on the XOR operator
"""

import numpy as np

# [input_1, input_2, ideal]
training_data = [
    [1, 0, 1],
    [0, 1, 1],
    [0, 0, 0],
    [1, 1, 0],
]

def sigmoid(x):
    """Sigmoid activation function"""
    return 1 / (1 + np.exp(-x))

def derivative(x):
    """Derivative of sigmoid activation function"""
    return np.exp(x) / (1 + np.exp(x))**2


class Neuron():
    """Neuron class that contains weights and other internal variables"""
    def __init__(self, name, inputs):
        self.name = name
        self.inputs = inputs
        self.total = 0
        self.weights = np.random.uniform(size=len(inputs) + 1) * 20 - 10
        self.delta_weights_prev = np.zeros(len(inputs) + 1)
        self.gradients = np.zeros(len(inputs) + 1)
        self.forwardweights = {}

    def forwardpropagate(self):
        """Forward propagates weights and biases to obtain a prediction"""
        inputs = []
        for index, neuron in enumerate(self.inputs):
            neuron.forwardweights[self.name] = self.weights[index]
            inputs.append(neuron.forwardpropagate())
        # tack on a [1] at the end for the bias neuron's output
        self.total = np.dot(inputs + [1], self.weights)
        self.value = sigmoid(self.total)
        return self.value

    def backpropagate(self, error):
        """Backpropagates error by computing gradients"""
        weight_totals = sum(self.forwardweights.values())
        layer_delta = -error * derivative(self.total) * weight_totals
        for index, neuron in enumerate(self.inputs):
            self.gradients[index] += layer_delta * neuron.value
            neuron.backpropagate(layer_delta)
        # update bias weight
        self.gradients[-1] += layer_delta

    def update_weights(self, learning_rate=0.7, momentum=0.3):
        """Updates weights based on gradients calculated by backpropagate()"""
        delta_weights = momentum * self.delta_weights_prev + learning_rate * self.gradients
        self.weights += delta_weights

        # store weight deltas and reset gradient sums
        self.delta_weights_prev = delta_weights
        self.gradients = np.zeros(len(self.inputs) + 1)


class Input():
    """Input neuron with some stubbed out functions that don't do anything"""
    def __init__(self, name, value):
        self.name = name
        self.value = value
        self.forwardweights = {}

    def forwardpropagate(self):
        return self.value

    def backpropagate(self, error):
        pass

    def update_weights():
        pass

# Build network
inputs = [Input("input1", 0), Input("input2", 0)]
hidden = [Neuron("hidden1", inputs), Neuron("hidden2", inputs)]
output = Neuron("output", hidden)

# dumb hack so output neuron's layer delta isn't 0
output.forwardweights["@@ OUTPUT NEURON @@"] = 1

mean_squared_error = np.inf

while mean_squared_error >= 0.05:
    errors = []
    for example in training_data:
        # Set the network's inputs
        inputs[0].value = example[0]
        inputs[1].value = example[1]

        predicted = output.forwardpropagate()
        ideal = example[2]

        error = predicted - ideal
        errors.append(error)
        output.backpropagate(error)
    output.update_weights()
    mean_squared_error = np.mean(np.square(errors))
    print(f"MSE: {mean_squared_error}")

# Validate results of training
print("Results:")
for example in training_data:
    inputs[0].value = example[0]
    inputs[1].value = example[1]

    predicted = output.forwardpropagate()
    print(f"f({example[0]}, {example[1]}) = {predicted}")
