/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuzzy;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;
//import net.sourceforge.jFuzzyLogic.rule.FuzzyRuleSet;

/**
 *
 * @author marina
 */
public class Fuzzy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // Load from 'FCL' file
        String fileName = "regras.fcl";
        FIS fis = FIS.load(fileName, true);

        Double natalidade[] = {21.55, 17.92, 22.41, 23.67, 15.42, 15.51, 16.03, 15.02, 15.12, 19.4, 13.18, 16.61, 16.76, 19.62, 15.24, 15.88, 16.06, 14.34, 13.1, 15.41, 16.84, 22.6, 12.28, 13.57, 16.55, 14.19, 18.2};
        Double mortalidade[] = {5.09, 7.22, 5.53, 4.53, 7.01, 7.11, 4.14, 5.49, 5.9, 7.3, 6.12, 5.91, 5.42, 5.75, 8.37, 7.61, 8.18, 5.89, 7.26, 6.5, 6.03, 5.52, 6.63, 5.04, 6.86, 5.82, 6.2};
        Double migracao[] = {0.6, 0.85, 0.2, 0.14, 0.63, 0.53, 0.43, 0.29, 0.4, 0.78, 0.23, 0.09, 0.13, 0.4, 0.54, 0.611, 0.63, 0.27, 0.29, 0.39, -0.03, 0.02, 0.25, 0.08, 0.45, 0.26, 0.25}; 
        
        DecimalFormat df = new DecimalFormat("0.00");
        
        // Error while loading?
        if (fis == null) {
            System.err.println("Can't load file: '" + fileName + "'");
            return;
        }

        FunctionBlock functionBlock = fis.getFunctionBlock(null);
        // Show 
        JFuzzyChart.get().chart(functionBlock);

        // Set inputs
        for (int i = 0; i < 27; i++) {
            fis.setVariable("natalidade", natalidade[i]);
            fis.setVariable("mortalidade", mortalidade[i]);
            fis.setVariable("migracao", migracao[i]);
                    
            // Evaluate
            fis.evaluate();
        
            System.out.println(df.format(fis.getVariable("crescimento").getValue()));
        }

        // Show output variable's chart
        JFuzzyChart.get().chart(functionBlock.getVariable("crescimento"), functionBlock.getVariable("crescimento").getDefuzzifier(), true);
        // Print ruleSet
        //System.out.println(fis);
        
    }
}
