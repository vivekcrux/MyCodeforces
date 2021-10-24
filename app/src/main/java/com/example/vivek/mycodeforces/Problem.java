package com.example.vivek.mycodeforces;

/**
 * Created by vivek on 15/7/18.
 */

public class Problem {
    public String contestId, index, name, solvedCount;
    Problem(String contestId, String index, String name, String solvedCount)
    {
        this.contestId = contestId;
        this.index = index;
        this.name = name;
        this.solvedCount = solvedCount;
    }
}
