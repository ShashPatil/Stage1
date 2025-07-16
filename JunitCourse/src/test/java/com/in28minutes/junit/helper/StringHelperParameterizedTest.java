package com.in28minutes.junit.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Arrays;
import java.util.Collection;

public class StringHelperParameterizedTest {

    StringHelper helper = new StringHelper();

    @ParameterizedTest
    @MethodSource("testConditionsProvider")
    void testTruncateAInFirst2Positions(String input, String expectedOutput) {
        // This test method does NOT need to change.
        assertEquals(expectedOutput, helper.truncateAInFirst2Positions(input));
    }

    // This static method now returns a Collection of String arrays,
    // which is a valid data source for @MethodSource.
    private static Collection<String[]> testConditionsProvider() {
        String[][] testData = {
            {"AACD", "CD"},
            {"ACD", "CD"}
        };
        return Arrays.asList(testData);
    }
}