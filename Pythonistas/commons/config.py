# -*- coding: utf-8 -*-
import os
import yaml

root_dir = os.path.dirname(__file__)[:-8]

# load app config
config = yaml.load(open(root_dir + '/config.yaml', 'r'))