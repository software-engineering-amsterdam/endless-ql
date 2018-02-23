import logging


def logging_basic_config(level):
    """ Setup basic config logging, useful for debugging """
    fmt = '%(filename)25s:%(lineno)-4d : %(levelname)-7s: %(message)s'
    logging.basicConfig(level=level, format=fmt)


def log_dictionary(dictionary, msg='', logger=None, level='debug', item_prefix='  '):
    """
    Writes a log message with key and value for each item in the dictionary.

    :param dictionary: the dictionary to be logged
    :type dictionary: dict
    :param logger: A logging.Logger object to log to. If not set, the 'main' logger is used.
    :type logger: logging.Logger or a string
    :param level: log level. String or int as described in the logging module documentation.
        Default: 'debug'.
    :type level: string or int
    :param item_prefix: String that will be prefixed to each line. Default: two spaces.
    :type item_prefix: string
    """
    level_nr = logging.getLevelName(level.upper())

    if logger is None:
        logger = logging.getLogger('main')

    if msg:
        logger.log(level_nr, "Logging dictionary: {}".format(msg))

    if not dictionary:
        logger.log(level_nr,"{}<empty dictionary>".format(item_prefix))
        return

    max_key_len = max([len(k) for k in dictionary.keys()])

    for key, value in sorted(dictionary.items()):
        logger.log(level_nr, "{0}{1:<{2}s} = {3}".format(item_prefix, key, max_key_len, value))
