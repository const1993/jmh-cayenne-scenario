package io.sample;

import io.sample.insert.TestInsert;
import io.sample.insert.TestRelatedInsert;
import io.sample.select.TestPrefetchSelect;
import io.sample.select.TestSimpleSelect;
import io.sample.select.TestWhereSelect;
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
    public void runJmhSelects() throws RunnerException {
        Options opt = getOptions()
                .include(TestSimpleSelect.class.getName() + ".*")
                .include(TestPrefetchSelect.class.getName() + ".*")
                .include(TestWhereSelect.class.getName()+ ".*")
                .build();

        new Runner(opt).run();
    }

//    @Test
//    public void runJmh() throws RunnerException {
//        Options opt = getOptions()
//                .include(TestInsert.class.getName() + ".*")
//                .include(TestRelatedInsert.class.getName() + ".*")
//                .build();
//
//        new Runner(opt).run();
//    }


    private ChainedOptionsBuilder getOptions() {
        String url = System.getProperty("cayenneJdbcUrl");
        String driver = System.getProperty("cayenneJdbcDriver");
        String username = System.getProperty("cayenneJdbcUsername");
        String password = System.getProperty("cayenneJdbcPassword");

        System.out.println("url: " + url);
        System.out.println("driver: " + driver);
        System.out.println("username: " + username);
        System.out.println("password: " + password);

        ChainedOptionsBuilder chainedOptionsBuilder = new OptionsBuilder();

        if (url != null && driver != null && username != null) {
            chainedOptionsBuilder
                    .param("url", url)
                    .param("driver", driver)
                    .param("username", username)
                    .param("password", password)
                    .param("isDefault", String.valueOf(false));
        }

        return chainedOptionsBuilder
                // Set the following options as needed
                .mode (Mode.AverageTime)
                .timeUnit(TimeUnit.MICROSECONDS)
                .warmupTime(TimeValue.seconds(2))
                .warmupIterations(5)
                .measurementTime(TimeValue.seconds(2))
                .measurementIterations(5)
                .threads(1)
                .forks(0)
                .shouldFailOnError(true)
                .shouldDoGC(true);
                //.jvmArgs("-XX:+UnlockDiagnosticVMOptions", "-XX:+PrintInlining")
                //.addProfiler(WinPerfAsmProfiler.class)
    }
}
