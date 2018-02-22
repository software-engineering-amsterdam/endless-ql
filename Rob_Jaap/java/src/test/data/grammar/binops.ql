form FormWithAllTypes {
   elementWithBoolean: "Did you sell a house in 2010?" money(a - b)
   elementWithInteger: "Did you buy a house in 2010?" money(a + b)
   elementWithMoney: "Did you enter a loan for maintenance/reconstruction?" money(c / d)
   elementWithString: "Did you enter a loan for maintenance/reconstruction?" money(e * f)
   elementWithMoney: "Did you enter a loan for maintenance/reconstruction?" money(c == d)
   elementWithString: "Did you enter a loan for maintenance/reconstruction?" money(e != f)
   elementWithMoney: "Did you enter a loan for maintenance/reconstruction?" money(c >= d)
   elementWithString: "Did you enter a loan for maintenance/reconstruction?" money(e > f)
   elementWithMoney: "Did you enter a loan for maintenance/reconstruction?" money(c < d)
   elementWithString: "Did you enter a loan for maintenance/reconstruction?" money(e <= f)
}
