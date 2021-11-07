package utils;

import org.bukkit.util.Vector;

public class Utils {
    public static boolean checkBetween(int value1, int value2, int numberToTest) {
        return Math.abs(value1 - numberToTest) + Math.abs(value2 - numberToTest) == Math.abs(value1 - value2);
    }

    public static Vector rotateVectorAroundY(Vector vector, double degrees) {
        double rad = Math.toRadians(degrees);

        double currentX = vector.getX();
        double currentZ = vector.getZ();

        double cosine = Math.cos(rad);
        double sine = Math.sin(rad);

        return new Vector((cosine * currentX - sine * currentZ), vector.getY(), (sine * currentX + cosine * currentZ));
    }
}
