package com.deutsch.drawing.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() { return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }


    public static final class Builder {

        private int x;
        private int y;

        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public Builder withX(int x) {
            this.x = x;
            return this;
        }

        public Builder withY(int y) {
            this.y = y;
            return this;
        }

        public Point build() {
            return new Point(x, y);
        }
    }
}
