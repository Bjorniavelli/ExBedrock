package com.bjorniavelli.exbedrock.data;

// Quick container class to hold config data.

public class DefaultConfigData
{
    public int index;
    public String[] drops;
    public String comment;
    public String category;

    public DefaultConfigData(int i, String c, String cat, String[] d)
    {
        index = i;
        comment = c;
        category = cat;
        drops = d;
    }
}
