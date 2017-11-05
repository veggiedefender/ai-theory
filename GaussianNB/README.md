# Project: Gaussian Naive Bayes

Read a file named "data.txt" with 2-dimensions of data points, sorted into classes. The file is just numbers seperated by spaces or newlines. Example:

```
0 5.43 0.1113
0 7.2234 1.975
1 6.54 -2.48
1 7.4329 -3.392
```

In the above example file, class 0 has two data points at (5.43, 0.1113) and (7.2234, 1.975). Class 1 has two data points at (6.54, -2.48) and (7.4329, -3.392)

Classes are ints, and points of data are doubles.

Read the file and use it as your training data. Afterwards, ask the user for data points and use Gaussian Naive Bayes to calculate probabiliites that the point is in a given class.

Example output:

```
Enter X:
4.33
Enter Y:
-1.45
Class 0 probability: 0.7234
Class 1 probability: 0.188
Class 2 probability: 0.0002
```

For simplicity, you can assume that the file has sequential classes starting at 0. (So, you might have classes 0,1, and 2, but you won't have classes 0,2,5 or something like that)
