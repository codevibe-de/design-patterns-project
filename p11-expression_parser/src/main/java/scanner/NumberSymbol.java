package scanner;

import java.util.Objects;

public class NumberSymbol implements Symbol {

    private final double value;

    public NumberSymbol(double value) {
        this.value = value;
    }

    public double value() {
        return this.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
        final NumberSymbol other = (NumberSymbol) obj;
        return Double.doubleToLongBits(this.value) == Double.doubleToLongBits(other.value);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [" + this.value + "]";
    }

}
