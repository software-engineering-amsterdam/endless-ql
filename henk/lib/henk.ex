defmodule Henk do
  
  @line ~s(
    form Box1HouseWarning {
      hasSoldHouse: "Did you sell something?" boolean
      hasNotHouse: "Did you sell something?" boolean
    }
  )

  def parse(str) do
    {:ok, tokens, _} = str |> to_charlist() |> :example_lexer.string()
    {:ok, list} = :example_parser.parse(tokens)
    list
  end

  def parseLine do
    parse @line
  end
end
