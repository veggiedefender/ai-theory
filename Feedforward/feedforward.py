import numpy as np

class Neuron:
    def __init__(self, inputs, weights, bias):
        self.inputs = inputs
        self.weights = weights
        self.bias = bias

    @staticmethod
    def activation(x):
        return 1 / (1 + np.exp(-x))

    def resolve(self):
        inputs = [ neuron.resolve() for neuron in self.inputs ]
        return Neuron.activation(np.dot(inputs, self.weights) + self.bias)

class InputNeuron:
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
