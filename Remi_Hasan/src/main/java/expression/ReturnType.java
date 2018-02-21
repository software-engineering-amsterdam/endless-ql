package expression;

public enum ReturnType {
    Integer {
        @Override
        public boolean sum(ReturnType other) {
            return other == Integer || other == Decimal || other == Money || other == Date;
        }

        @Override
        public boolean divide(ReturnType other) {
            return other == Integer || other == Decimal;
        }

        @Override
        public boolean ge(ReturnType other) {
            return other == Integer || other == Decimal || other == Money;
        }

        @Override
        public boolean eq(ReturnType other) {
            return other == Integer || other == Decimal || other == Money;
        }
    }, Decimal {
        @Override
        public boolean sum(ReturnType other) {
            return other == Integer || other == Decimal || other == Money;
        }

        @Override
        public boolean divide(ReturnType other) {
            return other == Integer || other == Decimal;
        }

        @Override
        public boolean ge(ReturnType other) {
            return other == Integer || other == Decimal || other == Money;
        }

        @Override
        public boolean eq(ReturnType other) {
            return other == Integer || other == Decimal || other == Money;
        }
    }, Boolean {
        @Override
        public boolean sum(ReturnType other) {
            return false;
        }

        @Override
        public boolean divide(ReturnType other) {
            return false;
        }

        @Override
        public boolean ge(ReturnType other) {
            return false;
        }

        @Override
        public boolean eq(ReturnType other) {
            return other == Boolean;
        }
    }, String {
        @Override
        public boolean sum(ReturnType other) {
            return false;
        }

        @Override
        public boolean divide(ReturnType other) {
            return false;
        }

        @Override
        public boolean ge(ReturnType other) {
            return false;
        }

        @Override
        public boolean eq(ReturnType other) {
            return other == String;
        }
    }, Money {
        @Override
        public boolean sum(ReturnType other) {
            return other == Integer || other == Decimal || other == Money;
        }

        @Override
        public boolean divide(ReturnType other) {
            return other == Integer || other == Decimal || other == Money;

        }

        @Override
        public boolean ge(ReturnType other) {
            return false;
        }

        @Override
        public boolean eq(ReturnType other) {
            return other == Integer || other == Decimal || other == Money;
        }
    }, Date {
        @Override
        public boolean sum(ReturnType other) {
            return other == Integer;
        }

        @Override
        public boolean divide(ReturnType other) {
            return false;
        }

        @Override
        public boolean ge(ReturnType other) {
            return other == Date;
        }

        @Override
        public boolean eq(ReturnType other) {
            return other == Date;
        }
    }, Undefined {
        @Override
        public boolean sum(ReturnType other) {
            return false;
        }

        @Override
        public boolean divide(ReturnType other) {
            return false;
        }

        @Override
        public boolean ge(ReturnType other) {
            return false;
        }

        @Override
        public boolean eq(ReturnType other) {
            return false;
        }
    };

    public abstract boolean sum(ReturnType other);

    public boolean subtract(ReturnType other) {
        return sum(other);
    }

    public abstract boolean divide(ReturnType other);

    public boolean multiply(ReturnType other) {
        return divide(other);
    }

    public abstract boolean ge(ReturnType other);

    public boolean gt(ReturnType other) {
        return ge(other);
    }

    public boolean le(ReturnType other) {
        return ge(other);
    }

    public boolean lt(ReturnType other) {
        return ge(other);
    }

    public abstract boolean eq(ReturnType other);

    public boolean and(ReturnType other) {
        return other == Boolean;
    }

    public boolean or(ReturnType other) {
        return this == Boolean && other == Boolean;
    }

    public boolean neg() {
        return this == Integer || this == Decimal || this == Money;
    }

    public boolean not() {
        return this == Boolean;
    }
}

