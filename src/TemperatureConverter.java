public class TemperatureConverter {
    private double tempC;
    private TemperatureScale unit;

    public TemperatureConverter(double tempC, TemperatureScale temperatureScale) {
        this.tempC = tempC;
        unit = temperatureScale;
    }

    public void setTempC(double tempC) {
        this.tempC = tempC;
    }

    public void setUnit(TemperatureScale unit) {
        this.unit = unit;
    }

    public double getTemperature() {
        double result = tempC;
        switch (unit) {
            case FAHRENHEIT:
                result = 32 + 1.8 * tempC;
            case KELVIN:
                result += 273.15;
        }
        return result;
    }

    public TemperatureScale getUnit() {
        return unit;
    }

    public void printTemparature() {
        System.out.println(tempC + "°" + TemperatureScale.CELSIUS.title +
                " = " + getTemperature() + "°" + unit.title);
    }

    enum TemperatureScale {
        CELSIUS("C"),
        FAHRENHEIT("F"),
        KELVIN("K");

        private String title;

        TemperatureScale(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }
}
