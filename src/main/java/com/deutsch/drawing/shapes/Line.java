package com.deutsch.drawing.shapes;

import com.deutsch.drawing.domain.Point;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.annotation.Nonnull;

public class Line extends Shape {

    public Line(@Nonnull Point point1, @Nonnull Point point2) {
        super(point1, point2);
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

        private Point point1;
        private Point point2;

        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public Builder withPoint1(Point point1) {
            this.point1 = point1;
            return this;
        }

        public Builder withPoint2(Point point2) {
            this.point2 = point2;
            return this;
        }

        public Line build() {
            return new Line(point1, point2);
        }
    }
}
