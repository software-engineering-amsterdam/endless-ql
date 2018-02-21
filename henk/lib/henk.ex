defmodule Henk do

  @line ~s(
    form Box1HouseWarning {
      hasSoldHouse: "Did you sell something?" boolean
      hasNotHouse: "Did you sell something?" boolean
    }
  )

  def parse(str) do
    {:ok, tokens, _} = str |> to_charlist() |> :lexer.string()
    {:ok, list} = :parser.parse(tokens)
    list
  end

  def parse_line do
    parse @line
  end
end
