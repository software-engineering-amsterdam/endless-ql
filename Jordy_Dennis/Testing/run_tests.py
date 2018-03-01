# execute all tests from test suite here

import unittest


def my_test_suite():
    test_loader = unittest.TestLoader()
    test_suite = test_loader.discover('Testing', pattern='*_test.py')
    return test_suite


if __name__ == '__main__':
    print("Running tests")
    unittest.TextTestRunner(verbosity=2).run(my_test_suite())