package expression;

public enum ReturnType {
    INTEGER {
        @Override
        public Boolean sum(ReturnType other) {
            return other == INTEGER || other == DECIMAL || other == MONEY || other == DATE;
        }

        @Override
        public Boolean divide(ReturnType other) {
            return other == INTEGER || other == DECIMAL;
        }

        @Override
        public Boolean ge(ReturnType other) {
            return other == INTEGER || other == DECIMAL || other == MONEY;
        }

        @Override
        public Boolean eq(ReturnType other) {
            return other == INTEGER || other == DECIMAL || other == MONEY;
        }
    }, DECIMAL {
        @Override
        public Boolean sum(ReturnType other) {
            return other == INTEGER || other == DECIMAL || other == MONEY;
        }

        @Override
        public Boolean divide(ReturnType other) {
            return other == INTEGER || other == DECIMAL;
        }

        @Override
        public Boolean ge(ReturnType other) {
            return other == INTEGER || other == DECIMAL || other == MONEY;
        }

        @Override
        public Boolean eq(ReturnType other) {
            return other == INTEGER || other == DECIMAL || other == MONEY;
        }
    }, BOOLEAN {
        @Override
        public Boolean sum(ReturnType other) {
            return false;
        }

        @Override
        public Boolean divide(ReturnType other) {
            return false;
        }

        @Override
        public Boolean ge(ReturnType other) {
            return false;
        }

        @Override
        public Boolean eq(ReturnType other) {
            return other == BOOLEAN;
        }
    }, STRING {
        @Override
        public Boolean sum(ReturnType other) {
            return false;
        }

        @Override
        public Boolean divide(ReturnType other) {
            return false;
        }

        @Override
        public Boolean ge(ReturnType other) {
            return false;
        }

        @Override
        public Boolean eq(ReturnType other) {
            return other == STRING;
        }
    }, MONEY {
        @Override
        public Boolean sum(ReturnType other) {
            return other == INTEGER || other == DECIMAL || other == MONEY;
        }

        @Override
        public Boolean divide(ReturnType other) {
            return other == INTEGER || other == DECIMAL || other == MONEY;

        }

        @Override
        public Boolean ge(ReturnType other) {
            return false;
        }

        @Override
        public Boolean eq(ReturnType other) {
            return other == INTEGER || other == DECIMAL || other == MONEY;
        }
    }, DATE {
        @Override
        public Boolean sum(ReturnType other) {
            return other == INTEGER;
        }

        @Override
        public Boolean divide(ReturnType other) {
            return false;
        }

        @Override
        public Boolean ge(ReturnType other) {
            return other == DATE;
        }

        @Override
        public Boolean eq(ReturnType other) {
            return other == DATE;
        }
    }, UNDEFINED {
        @Override
        public Boolean sum(ReturnType other) {
            return false;
        }

        @Override
        public Boolean divide(ReturnType other) {
            return false;
        }

        @Override
        public Boolean ge(ReturnType other) {
            return false;
        }

        @Override
        public Boolean eq(ReturnType other) {
            return false;
        }
    };

    public abstract Boolean sum(ReturnType other);

    public Boolean subtract(ReturnType other) {
        return sum(other);
    }

    public abstract Boolean divide(ReturnType other);

    public Boolean multiply(ReturnType other) {
        return divide(other);
    }

    public abstract Boolean ge(ReturnType other);

    public Boolean gt(ReturnType other) {
        return ge(other);
    }

    public Boolean le(ReturnType other) {
        return ge(other);
    }

    public Boolean lt(ReturnType other) {
        return ge(other);
    }

    public abstract Boolean eq(ReturnType other);

    public Boolean and(ReturnType other) {
        return other == BOOLEAN;
    }

    public Boolean or(ReturnType other) {
        return this == BOOLEAN && other == BOOLEAN;
    }

    public Boolean neg() {
        return this == INTEGER || this == DECIMAL || this == MONEY;
    }

    public Boolean not() {
        return this == BOOLEAN;
    }
}

