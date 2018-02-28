import os
import logging

file_name = 'log.txt'

try:
    os.remove(file_name)
except OSError:
    pass

logger = logging.getLogger(__name__)

fmt = '%(filename)14s: %(levelname)s: %(message)s'
logging.basicConfig(filename=file_name, level=logging.DEBUG, format=fmt)
