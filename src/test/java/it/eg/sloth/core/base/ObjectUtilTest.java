package it.eg.sloth.core.base;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjectUtilTest {

    @Test
    void coalesceTest() {
        assertEquals("Prova", ObjectUtil.coalesce("", null, "Prova", "Pippo"));
        assertEquals(null, ObjectUtil.coalesce("", null));
        assertEquals(null, ObjectUtil.coalesce());
        assertEquals(Double.valueOf(5), ObjectUtil.coalesce("", null, Double.valueOf(5)));
    }

    @Test
    void isNullTest() {
        assertTrue(ObjectUtil.isNull(null));
        assertTrue(ObjectUtil.isNull(""));
        assertFalse(ObjectUtil.isNull(Double.valueOf(0)));
    }
}
