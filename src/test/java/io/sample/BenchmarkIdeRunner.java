package io.sample;

import io.sample.insert.TestInsert;
import io.sample.insert.TestRelatedInsert;
import io.sample.select.TestPrefetchSelect;
import io.sample.select.TestSimpleSelect;
import org.junit.Test;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.ChainedOptionsBuilder;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.concurrent.TimeUnit;

public class BenchmarkIdeRunner {

    @Test
    public void runJmh() throws RunnerException {
        String url = System.getProperty("cayenneJdbcUrl");
        String driver = System.getProperty("cayenneJdbcDriver");
        String username = System.getProperty("cayenneJdbcUsername");
        String password = System.getProperty("cayenneJdbcPassword");

        System.out.println("url: " + url);
        System.out.println("driver: " + driver);
        System.out.println("username: " + username);
        System.out.println("password: " + password);

        ChainedOptionsBuilder chainedOptionsBuilder = new OptionsBuilder()

                // Specify which benchmarks to run.
                // You can be more specific if you'd like to run only one benchmark per test.
                .include(TestInsert.class.getName() + ".*")
                .include(TestRelatedInsert.class.getName() + ".*")
                .include(TestSimpleSelect.class.getName() + ".*")
                .include(TestPrefetchSelect.class.getName() + ".*");

        if (url != null || driver != null || username != null) {
            chainedOptionsBuilder
                    .param("url", url)
                    .param("driver", driver)
                    .param("username", username)
                    .param("password", password)
                    .param("isDefault", String.valueOf(false));
        }

        Options opt = chainedOptionsBuilder
                // Set the following options as needed
                .mode (Mode.AverageTime)
                .timeUnit(TimeUnit.MICROSECONDS)
                .warmupTime(TimeValue.seconds(1))
                .warmupIterations(5)
                .measurementTime(TimeValue.seconds(2))
                .measurementIterations(5)
                .threads(2)
                .forks(1)
                .shouldFailOnError(true)
                .shouldDoGC(true)
                //.jvmArgs("-XX:+UnlockDiagnosticVMOptions", "-XX:+PrintInlining")
                //.addProfiler(WinPerfAsmProfiler.class)
                .build();

        new Runner(opt).run();
    }

}
