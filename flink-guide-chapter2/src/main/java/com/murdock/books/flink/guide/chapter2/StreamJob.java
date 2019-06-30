package com.murdock.books.flink.guide.chapter2;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author weipeng2k 2019年06月30日 下午18:46:04
 */
public class StreamJob {
    public static void main(String[] args) throws Exception {
        // set up the streaming execution environment
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        /*
         * Here, you can start creating your execution plan for Flink.
         *
         * Start with getting some data from the environment, like
         * 	env.readTextFile(textPath);
         *
         * then, transform the resulting DataStream<String> using operations
         * like
         * 	.filter()
         * 	.flatMap()
         * 	.join()
         * 	.coGroup()
         *
         * and many more.
         * Have a look at the programming guide for the Java API:
         *
         * http://flink.apache.org/docs/latest/apis/streaming/index.html
         *
         */

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(StreamJob.class.getResourceAsStream("12factor")));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line);
            sb.append(" ");
        }

        DataStreamSource<String> stringDataStreamSource = env.fromElements(sb.toString().split(" "));
        stringDataStreamSource
                .map(word -> new WordWithCount(word, 1))
                .keyBy("word")
                .reduce((a, b) -> new WordWithCount(a.getWord(), a.getCount() + b.getCount()))
                .print();

        // execute program
        env.execute("Simple StreamJob");
    }
}
