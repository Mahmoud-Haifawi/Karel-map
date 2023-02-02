import stanford.karel.SuperKarel;

public class Homework extends SuperKarel {
    private static int RowCounter = 0, colCounter = 0, steps = 0, numberOfBeepers = 0, temp = 0;

    public void scanningMap() {
        // int RowCounter = 0, colCounter = 0;
        //counting columns
        steps = 0;
        colCounter = 0;
        RowCounter = 0;
        numberOfBeepers = 0;//today
        while (!frontIsBlocked()) {
            ++colCounter;
            moveWithIncreasingSteps();
            if (!frontIsClear()) {
                turnLeft();
                ++colCounter;
                break;
            }
        }
        //counting rows
        while (!frontIsBlocked()) {
            ++RowCounter;
            moveWithIncreasingSteps();
            if (!frontIsClear()) {
                turnLeft();
                ++RowCounter;
                break;
            }
        }
        //if the map  1*N or N*1
        if (RowCounter == 0) {
            if (colCounter == 0) {
                turnLeft();
                colCounter++;
            }

            while (!frontIsBlocked()) {
                ++RowCounter;
                moveWithIncreasingSteps();
                if (!frontIsClear()) {
                    ++RowCounter;
                    break;
                }
            }
            if (RowCounter == 0) {
                turnLeft();
                RowCounter++;
            }

        }

        System.out.println("Rows: " + RowCounter);
        System.out.println("Columns: " + colCounter);
    }

    public void walkHalfColumns() {
        if (colCounter % 2 == 0)
            for (int i = 0; i < colCounter / 2 - 1; i++) {
                moveWithIncreasingSteps();
            }
        else {
            for (int i = 0; i < colCounter / 2; i++) {
                moveWithIncreasingSteps();
            }
        }

    }

    public void walkFromTopToBottom() {
        while (!frontIsBlocked()) {
            if (!beepersPresent()) {
                putBeeperWithIncreasingBeepers();
            }
            moveWithIncreasingSteps();
            if (!frontIsClear()) {
                if (!beepersPresent()) {
                    putBeeperWithIncreasingBeepers();
                }
                turnRight();
                break;
            }
        }
    }

    public void walkHalfRows() {
        turnLeft();
        if (RowCounter % 2 == 0)
            for (int i = 0; i < RowCounter / 2 - 1; i++) {
                moveWithIncreasingSteps();
            }
        else {
            for (int i = 0; i < RowCounter / 2; i++) {
                moveWithIncreasingSteps();
            }
        }
    }

    public void walkFromLeftToRight() {
        if (RowCounter % 2 == 0)
            turnLeft();
        else
            turnAround();
        walkFromTopToBottom();
    }

    public void WalkDiagonally() {
        while (frontIsClear()) {
            if (!beepersPresent()) {
                putBeeperWithIncreasingBeepers();
            }
            moveWithIncreasingSteps();
            Left_Move();
            turnRight();
        }
        if (!beepersPresent()) {
            putBeeperWithIncreasingBeepers();

        }
        turnRight();

    }

    public void walkHoriznally() {
        if (temp < colCounter - 2 && frontIsClear())
            for (int i = 1; i < colCounter; i++) {
                temp++;
                if (frontIsClear()) {
                    moveWithIncreasingSteps();
                }
            }
    }

    public void TurnAroundWithWalkHalfRows() {
        turnAround();
        walkHalfRows();
    }

    public void restNumberOfRowsAndColumns() {
        walkFromLeftToRight();
        RowCounter = 0;
        colCounter = 0;
    }

    public void moveWithIncreasingSteps() {
        move();
        steps++;
    }

    public void putBeeperWithIncreasingBeepers() {
        putBeeper();
        numberOfBeepers++;
    }

    public void moveQuarterOfRows() {
        for (int i = 0; i < RowCounter / 4; i++) {
            moveWithIncreasingSteps();
        }
    }

    public void moveQuaterOfColumns() {
        for (int i = 0; i < colCounter / 4; i++) {
            moveWithIncreasingSteps();
        }
    }

    public void MoveAndPutBeepers() {
        putBeeperWithIncreasingBeepers();
        moveWithIncreasingSteps();
    }

    public void not_divisible_by_4(int temp2) {
        if (RowCounter % 4 != 0) {

            moveWithIncreasingSteps();

            if (RowCounter % 4 == 1) {
                MoveAndPutBeepers();
            } else if (RowCounter % 4 == 3) {
                for (int i = 0; i < 3; i++) {
                    moveWithIncreasingSteps();
                    MoveAndPutBeepers();
                }
            } else {
                for (int i = 0; i < temp2; i++) {

                    MoveAndPutBeepers();
                }
            }

        }
    }

    public void N1_N2_StartingPoint() {
        walkHalfColumns();
        turnLeft();
        walkFromTopToBottom();
        turnLeft();
    }

    public void N1_Column_algorithm() {
        N1_N2_StartingPoint();
        int temp2 = RowCounter - RowCounter / 2;
        for (int i = 0; i < RowCounter / 4 - 1; i++) {
            moveWithIncreasingSteps();
        }
        putBeeperWithIncreasingBeepers();
        moveQuarterOfRows();
        MoveAndPutBeepers();
        putBeeperWithIncreasingBeepers();
        moveQuarterOfRows();
        putBeeperWithIncreasingBeepers();
        temp2 = RowCounter % 4;
        not_divisible_by_4(temp2);


    }

    public void N1_Row_algorithm_() {
        N1_N2_StartingPoint();
        int temp2;
        if (colCounter % 4 == 0)
            moveQuaterOfColumns();
        else {
            for (int i = 0; i < colCounter / 4 + 1; i++) {
                moveWithIncreasingSteps();
            }
        }
        putBeeperWithIncreasingBeepers();
        turnAround();
        moveQuaterOfColumns();
        MoveAndPutBeepers();
        putBeeperWithIncreasingBeepers();
        moveQuaterOfColumns();
        putBeeperWithIncreasingBeepers();
        temp2 = colCounter % 4;
        not_divisible_by_4(temp2);
    }

    public void Left_Move() {
        turnLeft();
        moveWithIncreasingSteps();
    }

    public void generalRule() {
        numberOfBeepers = 0;
        if (colCounter < 2 && RowCounter < 2) {
            System.out.println("this map can be divided more than this");
        } else if ((RowCounter == colCounter && (colCounter % 2 == 0 && RowCounter % 2 == 0))) {
            WalkDiagonally();
            walkHoriznally();
            turnAround();
            WalkDiagonally();
        } else if (colCounter > 2 && RowCounter > 2) {
            walkHalfColumns();
            turnLeft();
            walkFromTopToBottom();
            if (RowCounter % 2 != 0 && colCounter % 2 != 0) {
                TurnAroundWithWalkHalfRows();
                turnRight();
                walkHalfColumns();
                restNumberOfRowsAndColumns();
            } else if (RowCounter % 2 != 0 && colCounter % 2 == 0) {
                moveWithIncreasingSteps();
                turnRight();
                walkFromTopToBottom();
                TurnAroundWithWalkHalfRows();
                Left_Move();
                walkHalfColumns();
                restNumberOfRowsAndColumns();

            } else if (RowCounter % 2 == 0 && colCounter % 2 != 0) {
                TurnAroundWithWalkHalfRows();
                walkFromLeftToRight();
                turnAround();
                walkFromLeftToRight();
                turnAround();
                moveWithIncreasingSteps();
                restNumberOfRowsAndColumns();
            } else if (colCounter % 2 == 0 && RowCounter % 2 == 0) {
                move();
                steps++;
                turnRight();
                walkFromTopToBottom();
                turnAround();
                walkHalfColumns();
                walkHalfRows();
                walkFromLeftToRight();
                move();
                steps++;
                turnAround();
                walkFromLeftToRight();
                colCounter = 0;
                RowCounter = 0;

            }

        } else {
            if (RowCounter >= 7) {
                if (colCounter == 1) {

                    N1_Column_algorithm();
                } else if (colCounter == 2) {
                    walkHalfRows();
                    if (RowCounter % 2 == 0) {
                        turnRight();
                        MoveAndPutBeepers();
                        turnLeft();
                        MoveAndPutBeepers();
                        turnLeft();
                        MoveAndPutBeepers();
                        turnLeft();
                        MoveAndPutBeepers();
                    } else if (RowCounter % 2 != 0) {
                        turnRight();
                        MoveAndPutBeepers();
                        turnLeft();
                        MoveAndPutBeepers();
                    }
                }
            } else if (colCounter > 7) {
                if (RowCounter == 1) {
                    N1_Row_algorithm_();
                } else if (RowCounter == 2) {
                }
            }
        }
    }

    @Override
    public void run() {
        setBeepersInBag(1000);
        scanningMap();
        generalRule();
        System.out.println("Steps:" + steps);
        System.out.println("numberOfBeepers: " + numberOfBeepers);

    }
}
