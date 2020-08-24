package com.youwiz.practices.common;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.*;

public class PageRequestTest {

    @Test
    public void setSizeTest() {
        final PageRequest page = new PageRequest();

        page.setSize(10);
        assertEquals(page.getSize(), 10);

        // 50이 넘어가면 기본사이즈 10
        page.setSize(51);
        assertEquals(page.getPage(), 0);    // 0 = 1
    }

    @Test
    public void setDirectionTest() {
        final PageRequest page = new PageRequest();
        page.setDirection(Sort.Direction.ASC);
        assertEquals(page.getDirection(), Sort.Direction.ASC);
    }

    @Test
    public void ofTest() {
        final PageRequest page = new PageRequest();
        page.setPage(1);
        page.setSize(10);
        page.setDirection(Sort.Direction.ASC);

        final org.springframework.data.domain.PageRequest pageRequest = page.of();

        assertEquals(pageRequest.getPageSize(), 10);
        assertEquals(pageRequest.getOffset(), 0L);
    }
}