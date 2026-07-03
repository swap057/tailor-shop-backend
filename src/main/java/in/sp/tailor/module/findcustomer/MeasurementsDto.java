package in.sp.tailor.module.findcustomer;

public class MeasurementsDto {
    // Shirt Measurements
    private String shirtLength;
    private double shirtFront;
    private double shirtShoulder;
    private double shirtSleeve;
    private String shirtCollar;
    private double shirtChest;
    private String shirtHalfSleeve;

    // Pant Measurements
    private String pantLength;
    private double pantBelowWaist;
    private double pantWaist;
    private double pantThigh;
    private double pantKnee;
    private String pantBottom;

    // Getters and Setters
    public String getShirtLength() { return shirtLength; }
    public void setShirtLength(String shirtLength) { this.shirtLength = shirtLength; }

    public double getShirtFront() { return shirtFront; }
    public void setShirtFront(double shirtFront) { this.shirtFront = shirtFront; }

    public double getShirtShoulder() { return shirtShoulder; }
    public void setShirtShoulder(double shirtShoulder) { this.shirtShoulder = shirtShoulder; }

    public double getShirtSleeve() { return shirtSleeve; }
    public void setShirtSleeve(double shirtSleeve) { this.shirtSleeve = shirtSleeve; }

    public String getShirtCollar() { return shirtCollar; }
    public void setShirtCollar(String shirtCollar) { this.shirtCollar = shirtCollar; }

    public double getShirtChest() { return shirtChest; }
    public void setShirtChest(double shirtChest) { this.shirtChest = shirtChest; }

    public String getShirtHalfSleeve() { return shirtHalfSleeve; }
    public void setShirtHalfSleeve(String shirtHalfSleeve) { this.shirtHalfSleeve = shirtHalfSleeve; }

    public String getPantLength() { return pantLength; }
    public void setPantLength(String pantLength) { this.pantLength = pantLength; }

    public double getPantBelowWaist() { return pantBelowWaist; }
    public void setPantBelowWaist(double pantBelowWaist) { this.pantBelowWaist = pantBelowWaist; }

    public double getPantWaist() { return pantWaist; }
    public void setPantWaist(double pantWaist) { this.pantWaist = pantWaist; }

    public double getPantThigh() { return pantThigh; }
    public void setPantThigh(double pantThigh) { this.pantThigh = pantThigh; }

    public double getPantKnee() { return pantKnee; }
    public void setPantKnee(double pantKnee) { this.pantKnee = pantKnee; }

    public String getPantBottom() { return pantBottom; }
    public void setPantBottom(String pantBottom) { this.pantBottom = pantBottom; }
}