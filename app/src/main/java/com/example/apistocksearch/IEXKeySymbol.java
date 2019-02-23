package com.example.apistocksearch;

public class IEXKeySymbol {

        private String symbol;
        private String companyName;
        private String sector;
        private String calculationPrice;


        public IEXKeySymbol(String symbol, String companyName, String sector, String calculationPrice){
            this.symbol = symbol;
            this.companyName = companyName;
            this.sector = sector;
            this.calculationPrice = calculationPrice;

        }
        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getSector() {
            return sector;
        }

        public void setSector(String sector) {
            this.sector = sector;
        }

        public String getCalculationPrice() {
            return calculationPrice;
        }

        public void setCalculationPrice(String calculationPrice) {
            this.calculationPrice = calculationPrice;
        }


    }


