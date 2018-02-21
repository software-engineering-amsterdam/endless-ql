import QuestionnaireForm from "../form/QuestionnaireForm";
import Form from "../form/Form";
import Field from "../form/field/Field";
import { QuestionnaireField } from "../form/field/QuestionnaireField";
import FieldType from "../form/field/FieldType";
import BooleanWrapper from "../form/values/BooleanWrapper";
import MoneyWrapper from "../form/values/MoneyWrapper";
import { Currencies, Money } from "ts-money";
import StringWrapper from "../form/values/StringWrapper";

/*
form Box1HouseOwning {
hasSoldHouse: “Did you sell a house in 2010?” boolean
hasBoughtHouse: “Did you by a house in 2010?” boolean
hasMaintLoan: “Did you enter a loan for maintenance/reconstruction?”
boolean
if (hasSoldHouse) {
sellingPrice: “Price the house was sold for:” money
privateDebt: “Private debts for the sold house:” money
valueResidue: “Value residue:” money(sellingPrice - privateDebt)
}
}
*/

const sampleFields: Field[] = [
  new QuestionnaireField({
    label: "Did you sell a house in 2010?",
    name: "hasSoldHouse",
    value: new BooleanWrapper(false),
    type: FieldType.Boolean
  }),
  new QuestionnaireField({
    label: "Did you by a house in 2010?",
    name: "hasBoughtHouse",
    value: new BooleanWrapper(true),
    type: FieldType.Boolean
  }),
  new QuestionnaireField({
    label: "How much should one Ripple be worth?",
    name: "howMuchOneRipple",
    value: new MoneyWrapper(new Money(1500, Currencies.EUR)),
    type: FieldType.Money
  }),
  new QuestionnaireField({
    label: "Some comments?",
    name: "comments",
    value: new StringWrapper(""),
    type: FieldType.Text
  })
];

export const sampleForm: Form = new QuestionnaireForm("Box1HouseOwning", sampleFields);