package com.vas;

import com.intuit.karate.junit5.Karate;

class TestRunner {
    @Karate.Test
    Karate testAll() {
        return Karate.run().relativeTo(getClass());
    }
}
