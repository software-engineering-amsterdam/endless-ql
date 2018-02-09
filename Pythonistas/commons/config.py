# -*- coding: utf-8 -*-
import os
import yaml

root_dir = os.path.dirname(__file__)[:-8]

# load server config, default in ./server_config.json
config = yaml.load(open(root_dir + '/config.yaml', 'r'))