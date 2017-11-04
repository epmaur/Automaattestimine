package utilities;

public class Constants {

    public enum COUNTRYCODE {
        EE, UK, US;

        public String toString() {
            switch(this){
                case EE :
                    return "EE";
                case UK:
                    return "UK";
                case US :
                    return "US";
            }
            return null;
        }
    }

    public enum UNIT {
        metric, imperial;

        public String toString() {
            switch (this) {
                case metric:
                    return "metric";
                case imperial:
                    return "imperial";
            }
            return null;
        }
    }



}

