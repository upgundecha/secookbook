import glob
from subprocess import Popen


tests = glob.glob('test*.py')
processes = [Popen('python %s' % test, shell=True) for test in tests]

for process in processes:
    process.wait()