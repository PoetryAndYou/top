package cn.poetryandyou.annodemo.study.testenum;

/**
 * 枚举类学习
 */
public enum TestEnum {
    SPRING, SUMMER, FALL, WINTER;
}

enum SeasonEnum {
    SPRING("春天"), SUMMER("夏天"), FALL("秋天"), WINTER("冬天");

    private final String name;

    private SeasonEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        String name = SeasonEnum.SPRING.getName();
        System.out.println(name);
    }

}

/**
 * 枚举实现接口
 */
enum Operation {
    PLUS {
        @Override
        public double eval(double x, double y) {
            return x + y;
        }

    },
    MINUS {
        @Override
        public double eval(double x, double y) {
            return x - y;
        }

    },
    TIMES {
        @Override
        public double eval(double x, double y) {
            return x * y;
        }

    },
    DIVIDE {
        @Override
        public double eval(double x, double y) {
            return x / y;
        }
    },

    TEST {
        @Override
        public double eval(double x, double y) {
            return x + y;
        }
    };

    /**
     * 抽象方法，由不同的枚举值提供不同的实现。
     *
     * @param x
     * @param y
     * @return
     */
    public abstract double eval(double x, double y);

    public static void main(String[] args) {
        System.out.println(Operation.PLUS.eval(10, 2));
        System.out.println(Operation.MINUS.eval(10, 2));
        System.out.println(Operation.TIMES.eval(10, 2));
        System.out.println(Operation.DIVIDE.eval(10, 2));
        System.out.println(Operation.TEST.eval(1, 3));
    }
}

/**
 * me枚举在switch中的使用测试
 */
class EnumTest {

    public void judge(TestEnum testEnum) {
        switch (testEnum) {
            case SPRING:
                System.out.println("春天");
                break;
            case FALL:
                System.out.println("秋天");
                break;
            case SUMMER:
                System.out.println("夏天");
                break;
            case WINTER:
                System.out.println("冬天");
                break;
            default:
                System.out.println("什么也不是！");
        }

    }

    public static void main(String[] args) {
        new EnumTest().judge(TestEnum.FALL);
    }
}

enum Weekday {
    SUN(0),

    MON(1), TUS(2),

    WED(3), THU(4),

    FRI(5), SAT(6);

    private int value;

    private Weekday(int value) {
        this.value = value;
    }

    public static Weekday getNextDay(Weekday nowDay) {
        int nextDayValue = ++nowDay.value % 7;
        return getWeekdayByValue(nextDayValue);

    }

    public static Weekday getWeekdayByValue(int value) {
        for (Weekday c : Weekday.values()) {
            if (c.value == value) {
                return c;
            }
        }
        return null;
    }
    public static void main(String[] args) {
        System.out.println("nowday ====> " + Weekday.SAT);
        System.out.println("nowday int ====> " + Weekday.SAT.ordinal());
        System.out.println("nextday ====> " + Weekday.getNextDay(Weekday.SAT)); // 输出 SUN
        for (Weekday weekday: Weekday.values()) {
            System.out.println(weekday+" ===> "+weekday.value);
        }
        //输出：
        //nowday ====> SAT
        //nowday int ====> 6
        //nextday ====> SUN
    }
}
/**
 * 枚举实现接口
 */

interface Behaviour {
    void print();

    String getInfo();
}

enum Color implements Behaviour {
    RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLO("黄色", 4);
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private Color(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 接口方法
    @Override
    public String getInfo() {
        return this.name;
    }

    // 接口方法
    @Override
    public void print() {
        System.out.println(this.index + ":" + this.name);
    }

    public static void main(String[] args) {
        Color.RED.print();
    }
}

enum TrafficLamp {
    RED(30) {
        @Override
        public TrafficLamp getNextLamp() {
            return GREEN;
        }
    }, GREEN(45) {
        @Override
        public TrafficLamp getNextLamp() {
            return YELLOW;
        }
    }, YELLOW(5) {
        @Override
        public TrafficLamp getNextLamp() {
            return RED;
        }
    };

    private int time;

    private TrafficLamp(int time) {
        this.time = time;
    }

    //一个抽象方法
    public abstract TrafficLamp getNextLamp();

    public static void main(String[] args) {
        TrafficLamp nextLamp = TrafficLamp.RED.getNextLamp();
        System.out.println(nextLamp);
        int time = TrafficLamp.RED.time;
        System.out.println(time);
    }
}

/**
 * 接口组织枚举
 */
interface Food {
    enum Coffee implements Food {
        BLACK_COFFEE, DECAF_COFFEE, LATTE, CAPPUCCINO
    }

    enum Dessert implements Food {
        FRUIT, CAKE, GELATO
    }
}

class FoolTest implements Food {
    public static void main(String[] args) {
        Coffee blackCoffee = Coffee.BLACK_COFFEE;
        System.out.println(blackCoffee.compareTo(Coffee.DECAF_COFFEE));
    }
}