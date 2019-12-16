package test;



import com.panamahitek.lamark.BillCalculator;
import com.panamahitek.PanamaHitek_DataBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class test {

    public static void main(String[] args) {

        PanamaHitek_DataBuffer buffer = new PanamaHitek_DataBuffer();
        buffer.addColumn(0, "Consumo", 0);
        buffer.addColumn(1, "Mes", 0);
        buffer.addColumn(2, "Año", 0);
        buffer.addColumn(3, "Empresa Distribuidora", String.class);
        buffer.addColumn(4, "Intervalo BTS", String.class);
        buffer.addColumn(5, "Cargo fijo", Double.class);
        buffer.addColumn(6, "Tarifa BTS", Double.class);
        buffer.addColumn(7, "Cargo por energía", Double.class);
        buffer.addColumn(8, "Tarifa CVC", Double.class);
        buffer.addColumn(9, "CVC", Double.class);
        buffer.addColumn(10, "Subtotal", Double.class);
        buffer.addColumn(11, "Tarifa FET", Double.class);
        buffer.addColumn(12, "Descuento FET", Double.class);
        buffer.addColumn(13, "Tarifa FTO", Double.class);
        buffer.addColumn(14, "Descuento FTO", Double.class);
        buffer.addColumn(15, "Descuento jubilados/tercera edad", Double.class);
        buffer.addColumn(16, "Tasa de subsidio por Ley 15", Double.class);
        buffer.addColumn(17, "Subsidio por Ley 15", Double.class);
        buffer.addColumn(18, "Tasa de cargo por Ley 15", Double.class);
        buffer.addColumn(19, "Cargo por Ley 15", Double.class);
        buffer.addColumn(20, "Subsidios y descuentos", Double.class);
        buffer.addColumn(21, "Cargos extra", Double.class);
        buffer.addColumn(22, "Total", Double.class);

        try {
            int energy = 641;
            BillCalculator bc = new BillCalculator();
            for (int i = 2015; i <= 2019; i++) {
                for (int j = 1; j <= 12; j++) {
                    bc.setParameters("ENSA", j, i, energy, 30, true);
                    bc.calculate();
                    buffer.addValue(0, energy);
                    buffer.addValue(1, j);
                    buffer.addValue(2, i);
                    buffer.addValue(3, bc.getCompanyAcronym());
                    buffer.addValue(4, "BTS"+bc.getBtsInterval());
                    buffer.addValue(5, bc.getFixedCharge());
                    buffer.addValue(6, bc.getBtsRate());
                    buffer.addValue(7, bc.getEnergyCharge());
                    buffer.addValue(8, bc.getCvcRate());
                    buffer.addValue(9, bc.getFuelCharge());
                    buffer.addValue(10, bc.getSubtotal());
                    buffer.addValue(11, bc.getFetRate());
                    buffer.addValue(12, bc.getFetDiscount());
                    buffer.addValue(13, bc.getFtoRate());
                    buffer.addValue(14, bc.getFtoDiscount());
                    buffer.addValue(15, bc.getRetiredDiscount());
                    buffer.addValue(16, bc.getLaw15SubsidyRate());
                    buffer.addValue(17, bc.getLaw15Discount());
                    buffer.addValue(18, bc.getLaw15ChargeRate());
                    buffer.addValue(19, bc.getLaw15Charge());
                    buffer.addValue(20, bc.getDiscounts());
                    buffer.addValue(21, bc.getExtraCharges());
                    buffer.addValue(22, bc.getTotal());

                    buffer.printRow();
                }
            }

            buffer.exportExcelFile("C:\\Users\\Antony Garcia\\Desktop\\cvc.xlsx");

        } catch (Exception ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
