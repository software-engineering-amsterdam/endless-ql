import { Currencies } from 'ts-money';

const constants = {
  DEFAULT_CURRENCY: Currencies.EUR,
  DEFAULT_DATE_FORMAT: "DD.MM.YYYY",
  COMPARABLE_TYPES: ["string", "number", "boolean", "Date"],
  APP_MODULE_TABS: {
    QLS: "QLS",
    QL: "QL"
  }
};

export default constants;