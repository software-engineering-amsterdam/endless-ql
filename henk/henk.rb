require_relative 'lib/hello_world'
require_relative 'lib/parser'
require_relative 'lib/ast'
require_relative 'lib/lexer'

require 'pry'

# Project base class for autoloading
module Henk; end

a = Henk::Parser.parse(Henk::Lexer.lex('1+1;'))
binding.pry
