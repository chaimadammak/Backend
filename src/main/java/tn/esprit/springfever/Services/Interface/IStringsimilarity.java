package tn.esprit.springfever.Services.Interface;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IStringsimilarity {
    public double calculateSimilarity(String s1, String s2) throws IOException;
}
