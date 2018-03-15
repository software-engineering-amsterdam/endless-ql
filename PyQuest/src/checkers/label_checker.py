from debug.debug import warning


class LabelChecker:
    def __init__(self, labels):
        for label in labels:
            if labels.count(label) > 1:
                warning(0, "Duplicate label detected")