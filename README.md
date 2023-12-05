# Pool games

## Note

In this realization:

1. the friction value in the configuration file is a multiplication factor. The smaller the value, the lower the friction. Too high a value will prevent the ball from moving. Valid range: `0 < friction < 1`.
2. Even though the drag display can be as long as you want when hitting the ball, there is a maximum value for the actual force applied.
3. The centers of the balls must be within the boundaries of the pockets, not just their rectangular boundaries in contact, for the balls to be considered in the pockets.

## Command Description

- Running `gradle run` will use the default configuration file in the `resources` folder. Use `gradle run --args="${CONFIG_PATH}"` to load a custom configuration file.
- Run `gradle javadoc` to generate automated documentation.

