package com.sber.javaschool.hometask1.converters;

public enum Temperatures {
    CELSIUS {
        public double toCelsius(double in) {
            return in;
        }

        public double fromCelsius(double inCelsius) throws BelowAbsoluteZeroException {
            if (inCelsius < MINIMUM_CELSIUS) {
                throw new BelowAbsoluteZeroException();
            }
            return inCelsius;
        }
    },
    KELVIN {
        public double toCelsius(double in) {
            return in + MINIMUM_CELSIUS;
        }

        public double fromCelsius(double inCelsius) {
            return inCelsius - MINIMUM_CELSIUS;
        }
    },
    FAHRENHEIT {
        public double toCelsius(double in) {
            return (in - 32) * 5 / 9;
        }

        public double fromCelsius(double inCelsius) {
            return inCelsius * (9 / 5d) + 32;
        }
    };

    static final double MINIMUM_CELSIUS = -273.15;

    abstract double fromCelsius(double temperatureCelsius) throws BelowAbsoluteZeroException;

    abstract double toCelsius(double temperature);

    public double convert(double in, Temperatures to) throws BelowAbsoluteZeroException {
        double inCelsius = this.toCelsius(in);
        return to.fromCelsius(inCelsius);
    }

    public static class BelowAbsoluteZeroException extends Exception {
        public BelowAbsoluteZeroException() {
            super("Температура ниже абсолютного нуля");
        }
    }
}
