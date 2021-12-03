package nl.omzwerving.oac.one;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SonarSweep {
    public static void main(String[] args) throws IOException {
        var sonarSweep = new SonarSweep();
        System.out.println(sonarSweep.countHigherMeasurements(sonarSweep.readMeasurements("day-1-sonarsweep")));
    }

    public List<Integer> readMeasurements(String filename) throws IOException {
        var measurementsAsString = FileUtils.readFileToString(new File(getClass().getClassLoader().getResource(filename).getFile()),
                Charset.defaultCharset());
        return Arrays.stream(measurementsAsString.split("\r\n")).map(Integer::valueOf).collect(Collectors.toList());
    }

    public int countHigherMeasurements(List<Integer> measurements) {
        int count = 0;
        var last = Integer.MIN_VALUE;
        for (var value : measurements) {
            if (value > last) {
                count++;
            }
            last = value;
        }
        return count;
    }
}
