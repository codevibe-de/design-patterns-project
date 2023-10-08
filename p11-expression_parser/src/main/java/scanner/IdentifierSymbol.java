package scanner;

import java.util.Objects;

public class IdentifierSymbol implements Symbol {

    private final String name;

    public IdentifierSymbol(String name) {
        this.name = Objects.requireNonNull(name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
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
        final IdentifierSymbol other = (IdentifierSymbol) obj;
        return Objects.equals(this.name, other.name);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [" + this.name + "]";
    }

}
