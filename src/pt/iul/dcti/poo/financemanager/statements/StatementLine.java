package pt.iul.dcti.poo.financemanager.statements;

import java.text.ParseException;
import java.util.Scanner;

import pt.iul.dcti.poo.financemanager.categories.Category;
import pt.iul.dcti.poo.financemanager.date.Date;
import pt.iul.dcti.poo.financemanager.exceptions.BadFormatException;
import pt.iul.dcti.poo.financemanager.statements.parsers.ScannerStatementLineParser;

public class StatementLine {

    public static final String DATE_FORMAT = "dd-MM-yyyy";

    public static StatementLine newStatementLine(Scanner s)
            throws ParseException, BadFormatException
    {
        return new ScannerStatementLineParser().parseStatementLine(s);
    }

    private Date     date;
    private Date     valueDate;
    private String   description;
    private double   draft;
    private double   credit;
    private double   accountingBalance;
    private double   availableBalance;
    private Category category;

    public StatementLine(Date date, Date valueDate, String description,
            double draft, double credit, double accountingBalance,
            double availableBalance, Category category)
    {
        setDate(date);
        setValueDate(valueDate);
        setDescription(description);
        setDraft(draft);
        setCredit(credit);
        setAccountingBalance(accountingBalance);
        setAvailableBalance(availableBalance);
        setCategory(category);
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }

    private void setAvailableBalance(double availableBalance)
    {
        this.availableBalance = availableBalance;
    }

    private void setAccountingBalance(double accountingBalance)
    {
        this.accountingBalance = accountingBalance;
    }

    private void setCredit(double credit)
    {
        if (credit < 0.0) {
            throw new IllegalArgumentException("Credit must be positive");
        }
        this.credit = credit;
    }

    private void setDraft(double draft)
    {
        if (draft > 0.0) {
            throw new IllegalArgumentException("Draft must be negative");
        }
        this.draft = draft;
    }

    public void setDate(Date date)
    {
        if (date == null) {
            throw new IllegalArgumentException("Date must not be null");
        }
        this.date = date;
    }

    public void setValueDate(Date valueDate)
    {
        if (valueDate == null) {
            throw new IllegalArgumentException("Value Date must not be null");
        }
        this.valueDate = valueDate;
    }

    public void setDescription(String description)
    {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Description must be a non empty String");
        }
        this.description = description;
    }

    public Date getDate()
    {
        return date;
    }

    public Date getValueDate()
    {
        return valueDate;
    }

    public String getDescription()
    {
        return description;
    }

    public double getCredit()
    {
        return credit;
    }

    public double getDraft()
    {
        return draft;
    }

    public double getAccountingBalance()
    {
        return accountingBalance;
    }

    public double getAvailableBalance()
    {
        return availableBalance;
    }

    public Category getCategory()
    {
        return category;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof StatementLine)) return false;
        
        StatementLine o = (StatementLine) obj;
        
        return
            getAccountingBalance() == o.getAccountingBalance() &&
            getAvailableBalance() == o.getAvailableBalance() &&
            getCredit() == o.getCredit() &&
            getDraft() == o.getDraft() &&
            getDate().compareTo(o.getDate()) == 0 &&
            getValueDate().compareTo(o.getValueDate()) == 0 &&
            getDescription().equals(o.getDescription());
    }

}