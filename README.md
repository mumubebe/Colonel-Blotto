# Colonel-Blotto
Solver for the game Colonel Blotto using a regret minimization-algorithm

From Wikipedia:
```
A Blotto game, Colonel Blotto game, or divide-a-dollar game is a type of two-person zero-sum game in which the players are tasked to simultaneously distribute limited resources over several objects (or battlefields). 

As an example Blotto game, consider the game in which two players each write down three positive integers in non-decreasing order and such that they add up to a pre-specified number S. Subsequently, the two players show each other their writings, and compare corresponding numbers. The player who has two numbers higher than the corresponding ones of the opponent wins the game.

For S = 6 only three choices of numbers are possible: (2, 2, 2), (1, 2, 3) and (1, 1, 4). It is easy to see that:

Any triplet against itself is a draw
(1, 1, 4) against (1, 2, 3) is a draw
(1, 2, 3) against (2, 2, 2) is a draw
(2, 2, 2) beats (1, 1, 4)

```



## Settings

<i>Abstractions.RESOURCES</i> - Total amount of resources divided between battlefields (S)

<i>Abstractions.BATTLEFIELD_VALUES</i> - Payoff value for each battlefield
