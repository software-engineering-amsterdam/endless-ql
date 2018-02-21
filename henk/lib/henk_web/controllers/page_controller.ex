defmodule HenkWeb.PageController do
  use HenkWeb, :controller

  def index(conn, _params) do
    render conn, "index.html"
  end
end
