package com.murdock.books.flink.guide.chapter2;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author weipeng2k 2019年06月30日 下午19:26:18
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class WordWithCount {

    private String word;

    private int count;

    public WordWithCount() {

    }
}
