package domain;

public class Delta {

    public double distance(double lat1, double lon1, double lat2, double lon2) {
        double r = 6371;
        double dLat = deg2rad(lat2 - lat1);
        double dLon = deg2rad(lon2 - lon1);
        double a
                = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = r * c; // Distance in km
        return d;
    }

    public double deg2rad(double deg) {
        return deg * (Math.PI / 180);
    }
    
    public static void main(String[] args) {
        
        Delta delta = new Delta();
        
        System.out.println(delta.distance(10,10, 40, 60));
        
    }

}