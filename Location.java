/*
Ryan Smit

wrapper class to pass and return positions in [][] as object references 
doesnt value check attributes

Oct 30 2021 - created
*/

public class Location
{
    private int row;
    private int column;

    public Location(int newRow, int newColumn)
    {
        row = newRow;
        column = newColumn;
    }

    public int getColumn()
    {
        return(column);
    }

    public int getRow()
    {
        return(row);
    }

    public void setColumn(int newColumn)
    {
        column = newColumn;
    }

    public void setRow(int newRow)
    {
        row = newRow;
    }
}