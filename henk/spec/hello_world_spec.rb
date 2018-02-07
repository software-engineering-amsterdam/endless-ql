RSpec.describe Henk::HelloWorld do
  it 'puts Hello World' do
    expect(Henk::HelloWorld.main).to eq('Hello World')
  end
end
