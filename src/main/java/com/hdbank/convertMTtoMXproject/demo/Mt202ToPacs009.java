package com.hdbank.convertMTtoMXproject.demo;

import com.hdbank.convertMTtoMXproject.iso2202.pacs00900111.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public class Mt202ToPacs009 {
    public Document translateToMxObject(SwiftMessage swiftMtMessage) {

        ArrayList<String> block1 = swiftMtMessage.block1;
        ArrayList<String> block2 = swiftMtMessage.block2;
        ArrayList<TagBlock3> block3 = swiftMtMessage.block3;
        ArrayList<TagBlock4> block4 = swiftMtMessage.block4;
        ArrayList<TagBlock5> block5 = swiftMtMessage.block5;

        // GrpHdr
        GroupHeader113 groupHeader113 = new GroupHeader113();
        groupHeader113.setMsgId(block4.get(0).getFieldData().get(0).toString());
        groupHeader113.setCreDtTm(DateUtils.createDateTimeNow());
        groupHeader113.setNbOfTxs("1");

        SettlementInstruction15 settlementInstruction15 = new SettlementInstruction15();
        settlementInstruction15.setSttlmMtd(SettlementMethod1Code.INDA);
        groupHeader113.setSttlmInf(settlementInstruction15);

        // CdtTrfTxInf
        CreditTransferTransaction62 creditTransferTransaction62 = new CreditTransferTransaction62();

        PaymentIdentification13 paymentIdentification13 = new PaymentIdentification13();
        paymentIdentification13.setInstrId(block4.get(0).getFieldData().get(0).toString());
        paymentIdentification13.setEndToEndId(block4.get(1).getFieldData().get(0).toString());
        paymentIdentification13.setUETR(block3.get(0).getValue());
        creditTransferTransaction62.setPmtId(paymentIdentification13);

        ActiveCurrencyAndAmount activeCurrencyAndAmount = new ActiveCurrencyAndAmount();
        activeCurrencyAndAmount.setCcy(block4.get(2).getFieldData().get(0).toString().substring(6, 9));
        activeCurrencyAndAmount.setValue(BigDecimal.valueOf(123.45));
        creditTransferTransaction62.setIntrBkSttlmAmt(activeCurrencyAndAmount);

        creditTransferTransaction62.setIntrBkSttlmDt(DateUtils.createDateTimeNow());

        SettlementDateTimeIndication1 settlementDateTimeIndication1 = new SettlementDateTimeIndication1();
        settlementDateTimeIndication1.setDbtDtTm(DateUtils.createDateTimeNow());
        creditTransferTransaction62.setSttlmTmIndctn(settlementDateTimeIndication1);

        FinancialInstitutionIdentification23 financialInstitutionIdentification23PrvsInstgAgt1 = new FinancialInstitutionIdentification23();
        financialInstitutionIdentification23PrvsInstgAgt1.setBICFI(block4.get(8).getFieldData().get(0).toString().substring(5));
        BranchAndFinancialInstitutionIdentification8 branchAndFinancialInstitutionIdentification8PrvsInstgAgt1 = new BranchAndFinancialInstitutionIdentification8();
        branchAndFinancialInstitutionIdentification8PrvsInstgAgt1.setFinInstnId(financialInstitutionIdentification23PrvsInstgAgt1);
        creditTransferTransaction62.setPrvsInstgAgt1(branchAndFinancialInstitutionIdentification8PrvsInstgAgt1);

        FinancialInstitutionIdentification23 financialInstitutionIdentification23InstgAgt = new FinancialInstitutionIdentification23();
        financialInstitutionIdentification23InstgAgt.setBICFI(block2.get(2));
        BranchAndFinancialInstitutionIdentification8 branchAndFinancialInstitutionIdentification8InstgAgt = new BranchAndFinancialInstitutionIdentification8();
        branchAndFinancialInstitutionIdentification8InstgAgt.setFinInstnId(financialInstitutionIdentification23InstgAgt);
        creditTransferTransaction62.setInstgAgt(branchAndFinancialInstitutionIdentification8InstgAgt);

        FinancialInstitutionIdentification23 financialInstitutionIdentification23InstdAgt = new FinancialInstitutionIdentification23();
        financialInstitutionIdentification23InstdAgt.setBICFI(block1.get(2));
        BranchAndFinancialInstitutionIdentification8 branchAndFinancialInstitutionIdentification8InstdAgt = new BranchAndFinancialInstitutionIdentification8();
        branchAndFinancialInstitutionIdentification8InstdAgt.setFinInstnId(financialInstitutionIdentification23InstdAgt);
        creditTransferTransaction62.setInstdAgt(branchAndFinancialInstitutionIdentification8InstdAgt);

        FinancialInstitutionIdentification23 financialInstitutionIdentification23IntrmyAgt1 = new FinancialInstitutionIdentification23();
        financialInstitutionIdentification23IntrmyAgt1.setBICFI(block4.get(5).getFieldData().get(0).toString());
        BranchAndFinancialInstitutionIdentification8 branchAndFinancialInstitutionIdentification8IntrmyAgt1 = new BranchAndFinancialInstitutionIdentification8();
        branchAndFinancialInstitutionIdentification8IntrmyAgt1.setFinInstnId(financialInstitutionIdentification23IntrmyAgt1);
        creditTransferTransaction62.setIntrmyAgt1(branchAndFinancialInstitutionIdentification8IntrmyAgt1);

        FinancialInstitutionIdentification23 financialInstitutionIdentification23Dbtr = new FinancialInstitutionIdentification23();
        financialInstitutionIdentification23Dbtr.setBICFI(block4.get(4).getFieldData().get(0).toString());
        BranchAndFinancialInstitutionIdentification8 branchAndFinancialInstitutionIdentification8Dbtr = new BranchAndFinancialInstitutionIdentification8();
        branchAndFinancialInstitutionIdentification8Dbtr.setFinInstnId(financialInstitutionIdentification23Dbtr);
        creditTransferTransaction62.setDbtr(branchAndFinancialInstitutionIdentification8Dbtr);

        FinancialInstitutionIdentification23 financialInstitutionIdentification23CdtrAgt = new FinancialInstitutionIdentification23();
        financialInstitutionIdentification23CdtrAgt.setBICFI(block4.get(6).getFieldData().get(0).toString());
        BranchAndFinancialInstitutionIdentification8 branchAndFinancialInstitutionIdentification8CdtrAgt = new BranchAndFinancialInstitutionIdentification8();
        branchAndFinancialInstitutionIdentification8CdtrAgt.setFinInstnId(financialInstitutionIdentification23CdtrAgt);
        creditTransferTransaction62.setCdtrAgt(branchAndFinancialInstitutionIdentification8CdtrAgt);

        FinancialInstitutionIdentification23 financialInstitutionIdentification23Cdtr = new FinancialInstitutionIdentification23();
        financialInstitutionIdentification23Cdtr.setBICFI(block4.get(7).getFieldData().get(0).toString());
        BranchAndFinancialInstitutionIdentification8 branchAndFinancialInstitutionIdentification8Cdtr = new BranchAndFinancialInstitutionIdentification8();
        branchAndFinancialInstitutionIdentification8Cdtr.setFinInstnId(financialInstitutionIdentification23Cdtr);
        creditTransferTransaction62.setCdtr(branchAndFinancialInstitutionIdentification8Cdtr);

        FinancialInstitutionCreditTransferV11 financialInstitutionCreditTransferV11 = new FinancialInstitutionCreditTransferV11();
        financialInstitutionCreditTransferV11.setGrpHdr(groupHeader113);
        ArrayList<CreditTransferTransaction62> creditTransferTransaction62s = new ArrayList<>();
        creditTransferTransaction62s.add(creditTransferTransaction62);
        financialInstitutionCreditTransferV11.setCdtTrfTxInf(creditTransferTransaction62s);
        Document document = new Document();
        document.setFICdtTrf(financialInstitutionCreditTransferV11);
        return document;
    }
}
