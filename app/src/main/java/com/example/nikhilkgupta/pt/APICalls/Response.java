package com.example.nikhilkgupta.pt.APICalls;

/**
 * Created by Nikhilk.Gupta on 24-09-2018.
 */

public class Response {
        private String ptTestName;

        private String testId;

        private String ptStartTime;

        public String getPtTestName ()
        {
            return ptTestName;
        }

        public void setPtTestName (String ptTestName)
        {
            this.ptTestName = ptTestName;
        }

        public String getTestId ()
        {
            return testId;
        }

        public void setTestId (String testId)
        {
            this.testId = testId;
        }

        public String getPtStartTime ()
        {
            return ptStartTime;
        }

        public void setPtStartTime (String ptStartTime)
        {
            this.ptStartTime = ptStartTime;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [ptTestName = "+ptTestName+", testId = "+testId+", ptStartTime = "+ptStartTime+"]";
        }
    }

