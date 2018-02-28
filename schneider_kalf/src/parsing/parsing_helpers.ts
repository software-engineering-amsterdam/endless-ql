export const getParserErrorMessage = (error: Error | any) => {
  let message = error.message;

  if (typeof error.location !== 'undefined') {
    message += ` Line: ${error.location.start.line}`;
  }

  return message;
};