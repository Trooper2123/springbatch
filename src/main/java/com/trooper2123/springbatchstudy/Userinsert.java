package com.trooper2123.springbatchstudy;

import com.trooper2123.springbatchstudy.config.BatchConfig;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class Userinsert {

    private static Scanner insert;
    private static BatchConfig batchConfig;

    public void initOddOrEven(){

        insert = new Scanner(System.in);

        System.out.println("Insert the amount of numbers:\n");
        System.out.print("Insert all numbers with space:\n");
        intoArray();
        batchConfig.printOddOrEvenJob();
        insert.close();

    }

    public Integer[] intoArray(){
        int n = insert.nextInt();
        Integer numbers[] = new Integer[n];
        for (int i=0; i < n;i++){
            numbers[i] = insert.nextInt();
        }
        return numbers;
    }

}
